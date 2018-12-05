package lessons_core_20_2.exception;

public class LimitExceeded extends BadRequestException {

	public LimitExceeded(String message) {
		super(message);
	}
}
