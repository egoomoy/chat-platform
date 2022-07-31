package aehdb.comm.exception;

/*
 * customException을 정의한다.
 * inner class로 구현..?
 * 
 */
public class CustomException {
	public static class NotFoundUserInfoException extends RuntimeException {
		private static final long serialVersionUID = -8152624378062985303L;
		private static final String message = "로그인 정보를 확인하세요.";

		public NotFoundUserInfoException() {
			super(message);
		}
	}

	public static class AccntidExistedException extends RuntimeException {
		private static final long serialVersionUID = -6485433258422835686L;
		private static final String message = "존재하는 사번입니다.";

		public AccntidExistedException() {
			super(message);
		}
	}
}
