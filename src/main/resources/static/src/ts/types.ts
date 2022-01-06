interface EmailAuthResponse {
    description :string,
    status : Status
}

enum Status{
    WAITING_AUTHORIZATION,
    SUCCESS,
    FAIL,
    DUPLICATE_MEMBER
}

export {EmailAuthResponse,Status}