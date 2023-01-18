package application.books;

public class UserInputException extends Exception{

    public UserInputException(String message) {
        super(message);
    }

    public static class TitleAlreadyExistsException extends UserInputException{
        public TitleAlreadyExistsException(){
            super("Error: Title already exists");
        }
    }

    public static class PagesNotNumberException extends UserInputException{
        public PagesNotNumberException(){
            super("Error: Pages must be a number");
        }
    }

    public static class InvalidRatingException extends UserInputException{
        public InvalidRatingException(){
            super("Error: Rating must be between 1 and 5");
        }
    }
}
