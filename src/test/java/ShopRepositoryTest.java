import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    Product item1 = new Product(124, "Молоко", 95);
    Product item2 = new Product(145, "Кофе", 999);
    Product item3 = new Product(543, "Конфеты", 155);
    Product item4 = new Product(675, "Жевачка", 25);
    Product item5 = new Product(543, "Салфетки", 75); // такой же id как у "item3"

    // тест на удаление
    @Test
    public void shouldRemoveById() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.removeById(item3.getId());
        Product[] expected = {item1, item2};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    // тест на вывод исключения удаления
    @Test
    public void shouldNotFindByID() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(-100);
        });
    }

    // тест на добавление
    @Test
    public void shouldAddProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        Product[] expected = {item1, item2, item3, item4};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    // тест на вывод исключения добавления
    @Test
    public void shouldNotAddProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item5);
        });
    }
}
