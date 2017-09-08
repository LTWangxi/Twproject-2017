package org.jblooming.system;

public interface SystemConstants {

  String SYSTEM = "SYSTEM";

// form fields

  String FLD_LOG_PLATFORM_LEVEL = "FLD_LOG_PLATFORM_LEVEL";
  String FLD_LOG_HIB_LEVEL = "FLD_LOG_HIB_LEVEL";
  String FLD_LOG_I18N_LEVEL = "FLD_LOG_I18N_LEVEL";
  String FLD_LOG_JOB_LEVEL = "FLD_LOG_JOB_LEVEL";
  String FLD_LOG_EMAIL_LEVEL = "FLD_LOG_EMAIL_LEVEL";
  String FLD_LOG_ON_CONSOLE = "FLD_LOG_ON_CONSOLE";
  String FLD_LOG_ON_FILE = "FLD_LOG_ON_FILE";

  String FLD_INDEX_EVERY_HOUR = SYSTEM + "FLD_INDEX_EVERY_HOUR";
  String FLD_MAX_FILE_SIZE = SYSTEM + "FLD_MAX_FILE_SIZE";
  String STORAGE_PATH_ALLOWED = "STORAGE_PATH_ALLOWED";

  String FLD_SERVER_TIME_ZONE = "SERVER_TIME_ZONE";

  String FLD_MAIL_SMTP = "MAIL_SMTP";
  String FLD_MAIL_SMTP_PORT = "MAIL_SMTP_PORT";
  String FLD_MAIL_SMTP_SSL = "MAIL_SMTP_SSL";
  String FLD_MAIL_SUBJECT = "MAIL_DEFAULT_SUBJECT";

  String FLD_MAIL_PROTOCOL = "FLD_MAIL_PROTOCOL";

  String FLD_MAIL_HELO_HOST = "MAIL_HELO_HOST";
  String FLD_MAIL_USE_AUTHENTICATED = "MAIL_USE_AUTHENTICATED";
  String FLD_MAIL_USER = "MAIL_USER";
  String FLD_MAIL_PWD = "MAIL_PWD";
  String FLD_MAIL_FROM = "MAIL_FROM";

  String FLD_POP3_HOST = "POP3_HOST";
  String FLD_POP3_USER = "POP3_USER";
  String FLD_POP3_PSW = "POP3_PSW";
  String FLD_POP3_PORT = "POP3_PORT";
  String FLD_EMAIL_DOWNLOAD_PROTOCOL = "EMAIL_DOWNLOAD_PROTOCOL";


  String FLD_REPOSITORY_URL = "REPOSITORY_URL";
  String UPLOAD_MAX_SIZE = "UPLOAD_MAX_SIZE";

  String AUDIT = "AUDIT";

  enum ENABLE_AUTHENTICATION_TYPE {ENABLE_STANDARD_AUTHENTICATION,ENABLE_HTTP_AUTHENTICATION,ENABLE_LDAP_AUTHENTICATION, ENABLE_LDAP_AUTHENTICATION_WITH_FALLBACK_ON_STANDARD  }

  String ENABLE_REDIR_AFTER_LOGIN = "ENABLE_REDIR_AFTER_LOGIN";
  String AUTHENTICATION_TYPE="AUTHENTICATION_TYPE";
  String DISABLE_COOKIE_LOGIN="DISABLE_COOKIE_LOGIN";


  String FLD_PASSWORD_EXPIRY = "FLD_PASSWORD_EXPIRY";
  String FLD_PASSWORD_MIN_LEN = "FLD_PASSWORD_MIN_LEN";

  String SATURDAY_IS_WORKING_DAY = "SATURDAY_IS_WORKING_DAY";
  String SUNDAY_IS_WORKING_DAY = "SUNDAY_IS_WORKING_DAY";
  String FRIDAY_IS_WORKING_DAY = "FRIDAY_IS_WORKING_DAY";

  String DOM_ID = "DOM_ID";

  String ALLOW_EMPTY_STRING_PSW = "ALLOW_EMPTY_STRING_PSW";

  String PUBLIC_SERVER_NAME="PUBLIC_SERVER_NAME";
  String PUBLIC_SERVER_PORT="PUBLIC_SERVER_PORT";
  String HTTP_PROTOCOL="HTTP_PROTOCOL";

  String SETUP_DB_UPDATE_DONE ="SETUP_DB_UPDATE_DONE";
  String SETUP_NOTIFIED_ADMIN_WIZARDS ="SETUP_NOTIFIED_ADMIN_WIZARDS";

  String CURRENCY_FORMAT="CURRENCY_FORMAT";

  String PRINT_LOGO="PRINT_LOGO";

  String ADMIN_MESSAGE = "ADMIN_MESSAGE";

  String PAGE_PLUGINS = "PAGE_PLUGINS";

}





