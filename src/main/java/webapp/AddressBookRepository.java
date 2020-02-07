package webapp;

import buddy.AddressBook;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "book", path = "book")
public interface AddressBookRepository extends PagingAndSortingRepository<AddressBook, Long> {
    List<AddressBook> findByName(@Param("name") String name);
}
