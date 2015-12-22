package parser;

public class ParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParserException(String msg) {
		super(msg);
	}

	public ParserException(String format, Object... args) {
		super(String.format(format, args));
	}

}
