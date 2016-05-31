package jm.lib.constants;


/**
 * 在实际的项目中应该使用自己的 ErrorCode implements 这个接口，
 * 在程序中应该使用 ErrorCode.* 而不是 BaseErrorCode.*
 *
 * @author caitco
 *
 */
public interface BaseErrorCode /* extends ErrorCoded */ {

    String SUCCESS                  = "error.success";
    String FAIL                  = "error.fail";

    /**
     * 必填的
     */
    String ARG_REQUIRED             = "error.required";
    String ARG_TOO_BIG              = "error.too_big";
    String NOT_EXISTS               = "error.not_exists";
    String AREADY_EXISTS            = "error.already_exists";


    String EXCLUDED                 = "error.excluded";  // ?
    /**
     *
     */
    String NOT_SUPPORTED            = "error.not_supported";
    
    String NEED_LOGIN               = "error.need_login";
    /**
     * Not authorized
     */
    String NOT_ALLOWED              = "error.not_allowed";

    /**
     * The system is under maintaining
     */
    String MAINTAIN                 = "error.maintain";
    String UNAVAILABLE_NOW          = "error.unavailable_now";



    String UNKNOWN                  = "error.unknown";

    String VALIDATE_INTEGER         = "error.validate.int";
    String VALIDATE_LONG            = "error.validate.long";
    String VALIDATE_DATE            = "error.validate.date";

}
