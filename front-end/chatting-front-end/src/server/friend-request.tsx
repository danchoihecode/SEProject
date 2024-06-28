'use server'

import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import {FriendRequest} from "@/components/notification-button";

export const handleAddFriend = async (id:string) =>{
    const session = await getServerSession(authOption as any) as Session
    const res = await fetch(`http://localhost:8080/friends/add_friend/${session.id}/${id}`, {
        method:'POST',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        cache:"no-store"
    })
    return res.ok;
}

interface AddFriendResponse {
    id:string,
    nickName:string,
}

export const listAddFriendRequest = async ():Promise<FriendRequest[]> =>{
    const session = await getServerSession(authOption as any) as Session
    const res = await fetch(`http://localhost:8080/friends/friend_request_list/${session.id}`, {
        method:'GET',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        cache:"no-store"
    })

    if(res.ok){
        const data:AddFriendResponse[] = await res.json()
        let returnData:FriendRequest[] = []
        for(let item of data){
            returnData.push({id:item.id,name:item.nickName})
        }
        console.log(returnData)
        return returnData
    }else {
        return []
    }
}

export const acceptFriendRequest = async (id:string) =>{
    const session = await getServerSession(authOption as any) as Session
    const res = await fetch(`http://localhost:8080/friends/accept_friend/${session.id}/${id}`, {
        method:'POST',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        cache:"no-store"
    })
    return res.ok;
}