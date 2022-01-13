interface EmailAuthResponse {
    description :string,
    status : Status
}

enum Status{
    WAITING_AUTHORIZATION = "WAITING_AUTHORIZATION",
    SUCCESS = "SUCCESS",
    FAIL = "FAIL",
    DUPLICATE_MEMBER ="DUPLICATE_MEMBER"
}

export {EmailAuthResponse,Status}