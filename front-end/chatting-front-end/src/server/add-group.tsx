'use server'
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import {Friend} from "@/components/side-bar";

export const listFriends = async ():Promise<Friend[]> =>{
    const session = await getServerSession(authOption as any) as Session
    const res = await fetch(`http://localhost:8080/friends/list/${session.id}`,{
        method:'GET',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        cache:"no-store"
    })
    if(res.ok) {
        return await res.json()
    }else {
        console.log(res)
    }
}
interface CreateGroupData{
    groupName:string,
    ownerId:string,
    selectedFriendIds:string[]
}

interface GroupProps{
    groupName:string,
    Ids:Friend[]
}
export const createGroup = async (props:GroupProps)=>{
    const session = await getServerSession(authOption as any) as Session

    const data: CreateGroupData = {
        groupName:props.groupName,
        ownerId:session.id,
        selectedFriendIds:[]
    }

    for(let e of props.Ids){
        data.selectedFriendIds.push(e.id)
    }

    const res = await fetch(`http://localhost:8080/groups`,{
        method:'POST',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        body:JSON.stringify(data),
        cache:"no-store"
    })
    return res.ok;
}