package webapp;

import buddy.AddressBook;
import buddy.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressBookRepository repository;
    @Autowired
    private BuddyInfoRepository buddyRepository;

    @GetMapping(value = "/create", produces = "application/json")
    @ResponseBody
    public Response create(@RequestParam(name="bookName", required=true) String bookName) {
        AddressBook ab = new AddressBook();
        ab.setName(bookName);
        repository.save(ab);
        return new Response("Create Success", Long.toString(ab.getId()));
    }

    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    public Response add(@RequestParam(name="bookName", required=true) String bookName,
                        @RequestParam(name="name", required=true) String name,
                        @RequestParam(name="address", required=true) String address,
                        @RequestParam(name="phoneNumber", required=true) String phoneNumber) {

        List<AddressBook> abL = repository.findByName(bookName);

        String resp = "";

        for (AddressBook ab : abL) {
            ab.addBuddy(new BuddyInfo(name, address, phoneNumber));
            repository.save(ab);
            resp += ab.toString() + ";";
        }
        return new Response("Add Buddy Success", resp);
    }

    @PostMapping(value = "/remove", produces = "application/json")
    @ResponseBody
    public Response remove(@RequestParam(name="bookName", required=true) String bookName,
                        @RequestParam(name="name", required=true) String name) {

        List<AddressBook> abL = repository.findByName(bookName);

        String resp = "";

        for (AddressBook ab : abL) {
            ab.removeBuddy(name);
            repository.save(ab);
            resp += ab.toString() + ";";
        }
        return new Response("Remove Buddy Success", resp);
    }


    @GetMapping("/view")
    public String view(@RequestParam(name="bookName", required=true) String bookName, Model model) {
        List<AddressBook> abL = repository.findByName(bookName);

        String resp = "";

        for (AddressBook ab : abL) {
            resp += ab.toString() + ";";
        }

        model.addAttribute("book", resp);
        return "view";
    }
}
