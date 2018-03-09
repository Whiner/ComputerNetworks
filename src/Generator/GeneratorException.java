package Generator;

public class GeneratorException extends Exception {

    private int CodeError;
    public int getCodeError() {
        return CodeError;
    }
    @Override
    public String getMessage() {
        return "Code: " + CodeError + "\nMessage: " + super.getMessage();
    }

    public GeneratorException(String message, int CodeError){
        super(message);
        this.CodeError = CodeError;
    }
    public GeneratorException(String message){
        super(message);
        CodeError = 0;
    }
}
