package com.nixsolutions.sql_jdbc.applications;

import com.nixsolutions.sql_jdbc.dao.implementations.*;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BDApplication {
    public static void main(String[] args) throws ParseException {
        //Create user_role
        System.out.println("test user role");
        UserRole userRole1 = new UserRole("user");
        UserRole userRole2 = new UserRole("admin");
        UserRole userRole3 = new UserRole("role_for_update");
        DAOInterface<UserRole> userRoleDAO = DAOFactory.getInstance()
                .getUserRoleDAO();

        userRoleDAO.add(userRole1);
        userRoleDAO.add(userRole2);
        userRoleDAO.add(userRole3);

        System.out.println("Get userRole by id");
        UserRole userRole = userRoleDAO.getById(3);
        System.out.println(userRole.getId() + " " + userRole.getRoleName());
        userRole3.setRoleName("role_for_delete");
        userRoleDAO.update(userRole3);
        System.out.println("com.nixsolutions.sql_jdbc.Update role.");
        System.out.println(userRole3.getId() + " " + userRole3.getRoleName());
        System.out.println(
                "com.nixsolutions.sql_jdbc.Delete userRole with id 3 and get all roles.");
        userRoleDAO.delete(3);
        List<UserRole> userRoles = userRoleDAO.getAll();
        for (UserRole userR : userRoles) {
            System.out.println(userR.getId() + " " + userR.getRoleName());
        }

        //Create user_login
        System.out.println("test user login");
        DAOInterface<UserLogin> userLoginDAO = DAOFactory.getInstance()
                .getUserLoginDAO();
        UserLogin userLogin1 = new UserLogin("NixelPixel", "12345bjjhbghvb",
                "ineedsomesleep@gmail.com");
        UserLogin userLogin2 = new UserLogin("yourDestroyer", "1003624gr7n",
                "dimaflower@gmail.com");
        UserLogin userLogin3 = new UserLogin("littleKitten", "738agags223",
                "nastya@nure.ua");
        UserLogin userLogin4 = new UserLogin("userLoginForUpdate",
                "sdksjd398893", "helloSun@gmail.com");
        UserLogin userLogin5 = new UserLogin("bigBrother", "1233jhbhsbdh8893",
                "brother@gmail.com");

        userLoginDAO.add(userLogin1);
        userLoginDAO.add(userLogin2);
        userLoginDAO.add(userLogin3);
        userLoginDAO.add(userLogin4);
        userLoginDAO.add(userLogin5);
        userLogin4.setLoginName("userLoginForDelete");
        userLoginDAO.update(userLogin4);
        System.out.println("Get updated user login with id 4");
        UserLogin u1 = userLoginDAO.getById(4);
        System.out.println(
                u1.getId() + " " + u1.getLoginName() + " " + u1.getPassword()
                        + " " + u1.getEmail());
        System.out.println(
                "com.nixsolutions.sql_jdbc.Delete user login with id 4 and get all login");
        userLoginDAO.delete(4);
        List<UserLogin> userLogins = userLoginDAO.getAll();
        for (UserLogin u : userLogins) {
            System.out.println(
                    u.getId() + " " + u.getLoginName() + " " + u.getPassword()
                            + " " + u.getEmail());
        }

        //Create system_user
        System.out.println("test system user");
        DAOInterface<SystemUser> systemUserDAO = DAOFactory.getInstance()
                .getSystemUserDAO();
        SystemUser systemUser = new SystemUser("Nadya", "Malitskaya", (long) 1,
                "BT818343", "380960887931", "Lenin str., 58",
                new GregorianCalendar(1999, Calendar.OCTOBER, 9).getTime(),
                (long) 1);
        SystemUser systemUser2 = new SystemUser("Dima", "Vasuk", (long) 2,
                "BC578456", "380667655903", "1561 Duis Rd.",
                new GregorianCalendar(1999, Calendar.NOVEMBER, 20).getTime(),
                (long) 1);
        SystemUser systemUser3 = new SystemUser("Oleg", "Karpenko", (long) 3,
                "BT954423", "380961418703", "7326 Elementum Rd.",
                new GregorianCalendar(1997, Calendar.JULY, 19).getTime(),
                (long) 2);
        SystemUser systemUser4 = new SystemUser("Vasya", "Popkin", (long) 5,
                "BT954555", "380961418897", "676 Elementum Rd.",
                new GregorianCalendar(2001, Calendar.MARCH, 03).getTime(),
                (long) 1);
        systemUserDAO.add(systemUser);
        systemUserDAO.add(systemUser2);
        systemUserDAO.add(systemUser3);
        systemUserDAO.add(systemUser4);

        System.out.println(
                "com.nixsolutions.sql_jdbc.Update and get system user with id 4");
        systemUser4.setLastName("Pupkin");
        systemUserDAO.update(systemUser4);
        SystemUser s1 = systemUserDAO.getById(4);
        System.out.println(
                s1.getId() + " " + s1.getFirstName() + " " + s1.getLastName()
                        + " " + s1.getLoginId() + " " + s1.getPassportNumber()
                        + " " + s1.getPhoneNumber() + " " + s1.getAddress()
                        + " " + s1.getBirthday() + " " + s1.getRoleId());

        System.out.println("com.nixsolutions.sql_jdbc.Delete and get all");
        systemUserDAO.delete(4);
        List<SystemUser> systemUsers = systemUserDAO.getAll();
        for (SystemUser s : systemUsers) {
            System.out.println(
                    s.getId() + " " + s.getFirstName() + " " + s.getLastName()
                            + " " + s.getLoginId() + " " + s.getPassportNumber()
                            + " " + s.getPhoneNumber() + " " + s.getAddress()
                            + " " + s.getBirthday() + " " + s.getRoleId());
        }

        //Create genre
        System.out.println("test genre");
        DAOInterface<Genre> genreDAO = DAOFactory.getInstance().getGenreDAO();
        Genre genre1 = new Genre("Philosophical novel",
                "Discoursing the function and role of society, the purpose of life, ethics or morals, and etc.");
        Genre genre2 = new Genre("Epic poetry",
                "Involving a time beyond living memory in which occurred the extraordinary doings of the extraordinary men and women who, in dealings with the gods or other superhuman forces.");
        Genre genre3 = new Genre("Fantasy",
                "A fiction set in a fictional universe, often inspired by real world myth and folklore.");
        Genre genre4 = new Genre("Modernism",
                "Characterized by a very self-conscious break with traditional ways of writing, in both poetry and prose fiction.");
        Genre genre5 = new Genre("Realist novel",
                "Attempts to represent familiar things as they are. Realist authors chose to depict everyday and banal activities and experiences.");
        Genre genre6 = new Genre("Poetry", "It is verbal art, mainly poetic");
        Genre genre7 = new Genre("Historical fiction",
                "The plot takes place in a setting located in the past.");
        Genre genre8 = new Genre("Tragedy",
                "A form of drama based on human suffering that invokes an accompanying catharsis or pleasure in audiences.");
        genreDAO.add(genre1);
        genreDAO.add(genre2);
        genreDAO.add(genre3);
        genreDAO.add(genre4);
        genreDAO.add(genre5);
        genreDAO.add(genre6);
        genreDAO.add(genre7);
        genreDAO.add(genre8);

        System.out.println(
                "com.nixsolutions.sql_jdbc.Update genre and get it by id");
        genre8.setName("Comedy");
        genre8.setDescription("Something than makes you smile.");
        genreDAO.update(genre8);
        Genre g = genreDAO.getById(4);
        System.out.println(
                g.getId() + " " + g.getName() + " " + g.getDescription());

        System.out.println(
                "com.nixsolutions.sql_jdbc.Delete genre and get all genres");
        genreDAO.delete(8);
        List<Genre> genres = genreDAO.getAll();
        for (Genre g1 : genres) {
            System.out.println(g1.getId() + " " + g1.getName() + " " + g1
                    .getDescription());
        }
        System.out.println();

        //Create book
        System.out.println("test book");
        DAOInterface<Book> bookDAO = DAOFactory.getInstance().getBookDAO();
        Book book1 = new Book("14524", "The Iliad", "english", 1);
        Book book2 = new Book("15647", "The Odyssey", "english", 1);
        Book book3 = new Book("40233", "Atlas Shrugged", "english", 3);
        Book book4 = new Book("40234", "Atlas Shrugged", "english", 1);
        Book book5 = new Book("40232", "Atlas Shrugged", "english", 2);
        Book book6 = new Book("12397", "Anna Karenina", "russian", 1);
        Book book7 = new Book("13244", "War and Peace", "russian", 3);
        Book book8 = new Book("1666", "Pesya", "russian", 1);
        Book book9 = new Book("45623", "Wuthering Heights", "deutsch", 1);
        bookDAO.add(book1);
        bookDAO.add(book2);
        bookDAO.add(book3);
        bookDAO.add(book4);
        bookDAO.add(book5);
        bookDAO.add(book6);
        bookDAO.add(book7);
        bookDAO.add(book8);
        bookDAO.add(book9);

        book8.setAddressInStorage("1235");
        bookDAO.update(book8);
        Book b = bookDAO.getById(8);
        System.out.println(
                b.getId() + " " + b.getAddressInStorage() + " " + b.getName()
                        + " " + b.getLanguage() + " " + b.getVolume());
        System.out.println();
        bookDAO.delete(8);
        List<Book> books = bookDAO.getAll();
        for (Book b1 : books) {
            System.out.println(
                    b1.getId() + " " + b1.getAddressInStorage() + " " + b1
                            .getName() + " " + b1.getLanguage() + " " + b1
                            .getVolume());
        }

        //Create book genre
        System.out.println("test book genre");
        DAOInterface<BookGenre> bookGenreDAO = DAOFactory.getInstance()
                .getBookGenreDAO();
        BookGenre bookGenre1 = new BookGenre((long) 1, (long) 6);
        BookGenre bookGenre2 = new BookGenre((long) 1, (long) 2);
        BookGenre bookGenre3 = new BookGenre((long) 2, (long) 2);
        BookGenre bookGenre4 = new BookGenre((long) 3, (long) 1);
        BookGenre bookGenre5 = new BookGenre((long) 4, (long) 1);
        BookGenre bookGenre6 = new BookGenre((long) 5, (long) 3);
        BookGenre bookGenre7 = new BookGenre((long) 6, (long) 5);
        BookGenre bookGenre8 = new BookGenre((long) 7, (long) 7);
        BookGenre bookGenre9 = new BookGenre((long) 9, (long) 3);

        bookGenreDAO.add(bookGenre1);
        bookGenreDAO.add(bookGenre2);
        bookGenreDAO.add(bookGenre3);
        bookGenreDAO.add(bookGenre4);
        bookGenreDAO.add(bookGenre5);
        bookGenreDAO.add(bookGenre6);
        bookGenreDAO.add(bookGenre7);
        bookGenreDAO.add(bookGenre8);
        bookGenreDAO.add(bookGenre9);

        bookGenre9.setGenreId((long) 3);
        bookGenreDAO.update(bookGenre9);
        BookGenre bg1 = bookGenreDAO.getById(9);
        System.out.println(bg1.getBookId() + " " + bg1.getGenreId());

        bookGenreDAO.delete(9);
        List<BookGenre> bookGenres = bookGenreDAO.getAll();
        for (BookGenre bg : bookGenres) {
            System.out.println(bg.getBookId() + " " + bg.getGenreId());
        }

        // Create author
        System.out.println("test author");
        DAOInterface<Author> authorDAO = DAOFactory.getInstance()
                .getAuthorDAO();
        Author author1 = new Author("Leo", null, "Tolstoy", "male",
                (new GregorianCalendar(1828, Calendar.OCTOBER, 9).getTime()));
        Author author2 = new Author("Ayn", null, "Rand", "female",
                (new GregorianCalendar(1905, Calendar.FEBRUARY, 2).getTime()));
        Author author3 = new Author("Homer", null, null, "male", null);
        Author author4 = new Author("Alex", null, "Pushkin", "male",
                (new GregorianCalendar(1799, Calendar.MAY, 26).getTime()));
        Author author5 = new Author("Emily", "Jane", "Bronte", "female",
                (new GregorianCalendar(1818, Calendar.JULY, 30).getTime()));

        authorDAO.add(author1);
        authorDAO.add(author2);
        authorDAO.add(author3);
        authorDAO.add(author4);
        authorDAO.add(author5);

        author4.setFirstName("Alexander");
        authorDAO.update(author4);
        Author a = authorDAO.getById(4);
        System.out.println(
                a.getId() + " " + a.getFirstName() + " " + a.getMiddleName()
                        + " " + a.getLastName() + " " + a.getSex() + " " + a
                        .getBirthday());

        // authorDAO.delete(4);
        List<Author> authors = authorDAO.getAll();
        for (Author a1 : authors) {
            System.out.println(a1.getId() + " " + a1.getFirstName() + " " + a1
                    .getMiddleName() + " " + a1.getLastName() + " " + a1
                    .getSex() + " " + a1.getBirthday());
        }

        //Create book author
        System.out.println("test book author");
        DAOInterface<BookAuthor> bookAuthorDAO = DAOFactory.getInstance()
                .getBookAuthorDAO();
        BookAuthor bookAuthor1 = new BookAuthor((long) 1, (long) 3);
        BookAuthor bookAuthor2 = new BookAuthor((long) 2, (long) 3);
        BookAuthor bookAuthor3 = new BookAuthor((long) 3, (long) 2);
        BookAuthor bookAuthor4 = new BookAuthor((long) 4, (long) 2);
        BookAuthor bookAuthor5 = new BookAuthor((long) 5, (long) 2);
        BookAuthor bookAuthor6 = new BookAuthor((long) 6, (long) 1);
        BookAuthor bookAuthor7 = new BookAuthor((long) 7, (long) 2);
        BookAuthor bookAuthor8 = new BookAuthor((long) 9, (long) 5);

        bookAuthorDAO.add(bookAuthor1);
        bookAuthorDAO.add(bookAuthor2);
        bookAuthorDAO.add(bookAuthor3);
        bookAuthorDAO.add(bookAuthor4);
        bookAuthorDAO.add(bookAuthor5);
        bookAuthorDAO.add(bookAuthor6);
        bookAuthorDAO.add(bookAuthor7);
        bookAuthorDAO.add(bookAuthor8);

        bookAuthor7.setAuthorId((long) 1);
        bookAuthorDAO.update(bookAuthor7);
        BookAuthor ba = bookAuthorDAO.getById(7);
        System.out.println(
                ba.getId() + " " + ba.getBookId() + " " + ba.getAuthorId());

        bookAuthorDAO.delete(7);
        List<BookAuthor> bookAuthors = bookAuthorDAO.getAll();
        for (BookAuthor ba1 : bookAuthors) {
            System.out.println(ba1.getId() + " " + ba1.getBookId() + " " + ba1
                    .getAuthorId());
        }

        // Create Composition comment
        System.out.println("test composition comment");
        DAOInterface<CompositionComment> compositionCommentDAO = DAOFactory
                .getInstance().getCompositionCommentDAO();
        CompositionComment compositionComment1 = new CompositionComment(
                (long) 1, (long) 5,
                "A very great ending!!! I am very impressed!",
                new GregorianCalendar(2017, Calendar.MARCH, 24).getTime());
        CompositionComment compositionComment2 = new CompositionComment(
                (long) 2, (long) 7,
                "I think, it is a very boring book with a lot of water inside imho...",
                new GregorianCalendar(2018, Calendar.MAY, 13).getTime());
        CompositionComment compositionComment3 = new CompositionComment(
                (long) 1, (long) 7,
                "I do not agree with Dima Vasuk, a very deep work that leaves the reader impressed for a long time.",
                new GregorianCalendar(2018, Calendar.JULY, 12).getTime());
        CompositionComment compositionComment4 = new CompositionComment(
                (long) 1, (long) 1, "nice",
                new GregorianCalendar(2017, Calendar.AUGUST, 12).getTime());

        compositionCommentDAO.add(compositionComment1);
        compositionCommentDAO.add(compositionComment2);
        compositionCommentDAO.add(compositionComment3);
        compositionCommentDAO.add(compositionComment4);

        compositionComment4.setComment(
                "I think i will read this book again!! Very nice!!!");
        System.out.println(
                "com.nixsolutions.sql_jdbc.Update and get by id composition comment");
        compositionCommentDAO.update(compositionComment4);
        CompositionComment c = compositionCommentDAO.getById(4);
        System.out.println(
                c.getId() + " " + c.getUserId() + " " + c.getBookId() + " " + c
                        .getComment() + " " + c.getCommentDate());

        System.out.println(
                "com.nixsolutions.sql_jdbc.Delete comment and get all comments");
        compositionCommentDAO.delete(4);
        List<CompositionComment> compositionComments = compositionCommentDAO
                .getAll();
        for (CompositionComment c1 : compositionComments) {
            System.out.println(
                    c1.getId() + " " + c1.getUserId() + " " + c1.getBookId()
                            + " " + c1.getComment() + " " + c1
                            .getCommentDate());
        }

        //Create item status
        System.out.println("Test item status");
        DAOInterface<ItemStatus> itemStatusDAO = DAOFactory.getInstance()
                .getItemStatusDAO();
        ItemStatus itemStatus1 = new ItemStatus("In a storage");
        ItemStatus itemStatus2 = new ItemStatus("Booked");
        ItemStatus itemStatus3 = new ItemStatus("Status to update");

        itemStatusDAO.add(itemStatus1);
        itemStatusDAO.add(itemStatus2);
        itemStatusDAO.add(itemStatus3);

        itemStatus3.setName("Status to delete");
        itemStatusDAO.update(itemStatus3);
        ItemStatus is = itemStatusDAO.getById(3);
        System.out.println(is.getId() + " " + is.getName());

        itemStatusDAO.delete(3);
        List<ItemStatus> itemStatuses = itemStatusDAO.getAll();
        for (ItemStatus is1 : itemStatuses) {
            System.out.println(is1.getId() + " " + is1.getName());
        }

        //Create item
        DAOInterface<Item> itemDAO = DAOFactory.getInstance().getItemDAO();
        Item item1 = new Item(1997, "Yellow yard", (long) 1, (long) 1);
        Item item2 = new Item(1995, "Yellow yard", (long) 2, (long) 2);
        Item item3 = new Item(1999, "Virago", (long) 6, (long) 2);
        Item item4 = new Item(2001, "Virago", (long) 5, (long) 2);
        Item item5 = new Item(2001, "Virago", (long) 4, (long) 1);
        Item item6 = new Item(2007, "Picador", (long) 5, (long) 1);
        Item item7 = new Item(2005, "Picador", (long) 3, (long) 1);
        Item item8 = new Item(2006, "Picador", (long) 4, (long) 1);
        Item item9 = new Item(1995, "Random House", (long) 7, (long) 2);
        Item item10 = new Item(1995, "Random House", (long) 8, (long) 1);
        Item item11 = new Item(2007, "Random House", (long) 2, (long) 1);

        itemDAO.add(item1);
        itemDAO.add(item2);
        itemDAO.add(item3);
        itemDAO.add(item4);
        itemDAO.add(item5);
        itemDAO.add(item6);
        itemDAO.add(item7);
        itemDAO.add(item8);
        itemDAO.add(item9);
        itemDAO.add(item10);
        itemDAO.add(item11);

        item11.setPublishOffice("Some office");
        itemDAO.update(item11);
        Item i = itemDAO.getById(11);
        System.out.println(i.getId() + " " + i.getPublishYear() + " " + i
                .getPublishOffice() + " " + i.getBookId() + " " + i
                .getItemStatusId());

        itemDAO.delete(11);
        List<Item> items = itemDAO.getAll();
        for (Item i1 : items) {
            System.out.println(i1.getId() + " " + i1.getPublishYear() + " " + i1
                    .getPublishOffice() + " " + i1.getBookId() + " " + i1
                    .getItemStatusId());
        }

        //Create status comment
        System.out.println("test status comment");
        DAOInterface<StatusComment> statusCommentDAO = DAOFactory.getInstance()
                .getStatusCommentDAO();
        StatusComment statusComment = new StatusComment((long) 1, (long) 2,
                "Not great, not terrible!",
                new GregorianCalendar(2017, Calendar.MARCH, 25).getTime());
        StatusComment statusComment1 = new StatusComment((long) 2, (long) 10,
                "A very old and bad condition of a book!",
                new GregorianCalendar(2018, Calendar.MAY, 11).getTime());
        StatusComment statusComment2 = new StatusComment((long) 1, (long) 7,
                "Surprisingly the book is in a good condition!",
                new GregorianCalendar(2017, Calendar.DECEMBER, 9).getTime());
        StatusComment statusComment3 = new StatusComment((long) 1, (long) 6,
                "I did not find any disadvantages!",
                new GregorianCalendar(2018, Calendar.JULY, 13).getTime());
        StatusComment statusComment4 = new StatusComment((long) 2, (long) 7,
                "I do not like your website!!!!!",
                new GregorianCalendar(2018, Calendar.JULY, 2).getTime());

        statusCommentDAO.add(statusComment1);
        statusCommentDAO.add(statusComment2);
        statusCommentDAO.add(statusComment3);
        statusCommentDAO.add(statusComment4);

        statusComment4
                .setComment("com.nixsolutions.sql_jdbc.Delete your web site!");
        StatusComment sc1 = statusCommentDAO.getById(4);
        System.out.println(
                sc1.getId() + " " + sc1.getUserId() + " " + sc1.getItemId()
                        + " " + sc1.getComment() + " " + sc1.getCommentDate());

        statusCommentDAO.delete(4);
        List<StatusComment> statusComments = statusCommentDAO.getAll();
        for (StatusComment sc : statusComments) {
            System.out.println(
                    sc1.getId() + " " + sc.getUserId() + " " + sc.getItemId()
                            + " " + sc.getComment() + " " + sc
                            .getCommentDate());
        }

        // Create order status
        System.out.println("test order status");
        DAOInterface<OrderStatus> orderStatusDAO = DAOFactory.getInstance()
                .getOrderStatusDAO();
        OrderStatus orderStatus1 = new OrderStatus("Accepted");
        OrderStatus orderStatus2 = new OrderStatus("On the way");
        OrderStatus orderStatus3 = new OrderStatus("Delivered");
        OrderStatus orderStatus4 = new OrderStatus("Returned");
        OrderStatus orderStatus5 = new OrderStatus("UpdateStatus");

        orderStatusDAO.add(orderStatus1);
        orderStatusDAO.add(orderStatus2);
        orderStatusDAO.add(orderStatus3);
        orderStatusDAO.add(orderStatus4);
        orderStatusDAO.add(orderStatus5);

        orderStatus5.setName("DeleteStatus");
        orderStatusDAO.update(orderStatus5);
        OrderStatus orderStatus = orderStatusDAO.getById(5);
        System.out.println(orderStatus.getId() + " " + orderStatus.getName());

        orderStatusDAO.delete(5);
        List<OrderStatus> orderStatuses = orderStatusDAO.getAll();
        for (OrderStatus os : orderStatuses) {
            System.out.println(os.getId() + " " + os.getName());

        }

        // Create user order
        System.out.println("test user order");
        DAOInterface<UserOrder> userOrderDAO = DAOFactory.getInstance()
                .getUserOrderDAO();
        UserOrder userOrder1 = new UserOrder((long) 1, (long) 2,
                "Lenin str., 58",
                new GregorianCalendar(2017, Calendar.FEBRUARY, 14).getTime(),
                new GregorianCalendar(2017, Calendar.MARCH, 20).getTime(),
                (long) 4);
        UserOrder userOrder2 = new UserOrder((long) 1, (long) 3,
                "Lenin str., 58",
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 15).getTime(),
                new GregorianCalendar(2019, Calendar.OCTOBER, 15).getTime(),
                (long) 2);
        UserOrder userOrder3 = new UserOrder((long) 2, (long) 4,
                "Adway St., 24",
                new GregorianCalendar(2018, Calendar.MARCH, 3).getTime(),
                new GregorianCalendar(2018, Calendar.MARCH, 3).getTime(),
                (long) 3);
        UserOrder userOrder4 = new UserOrder((long) 2, (long) 9, "Risus St. 4",
                new GregorianCalendar(2019, Calendar.OCTOBER, 10).getTime(),
                new GregorianCalendar(2019, Calendar.NOVEMBER, 11).getTime(),
                (long) 1);
        userOrderDAO.add(userOrder1);
        userOrderDAO.add(userOrder2);
        userOrderDAO.add(userOrder3);
        userOrderDAO.add(userOrder4);

        userOrder4.setAddress("Papay str., 18");
        userOrderDAO.update(userOrder4);
        UserOrder userOrder = userOrderDAO.getById(4);
        System.out.println(userOrder.getId() + " " + userOrder.getUserId() + " "
                + userOrder.getItemId() + " " + userOrder.getOrderDate() + " "
                + userOrder.getReturnDate() + " " + userOrder
                .getOrderStatusId());

        userOrderDAO.delete(4);
        List<UserOrder> userOrders = userOrderDAO.getAll();
        for (UserOrder u : userOrders) {
            System.out.println(
                    u.getId() + " " + u.getUserId() + " " + u.getItemId() + " "
                            + u.getOrderDate() + " " + u.getReturnDate() + " "
                            + u.getOrderStatusId());
        }
    }

}

