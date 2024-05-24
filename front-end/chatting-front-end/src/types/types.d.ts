declare type ResponseData = {
    error:string,
    ok:boolean,
    status:number,
    url:string
}

declare type LoginResponseData={
    error:object,
    token:string,
    expiresIn:number
}

declare type RegisterResponseData = any