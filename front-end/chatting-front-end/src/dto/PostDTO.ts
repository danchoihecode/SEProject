export interface PostDTO {
    reportID: string;
    userName :string;
    postText:string;
    reason:string
}
export interface UserDTO {
    userName :string;
    reason:string;
    duration:number;
    admin:string;
}