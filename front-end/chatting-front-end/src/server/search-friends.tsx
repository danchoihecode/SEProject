'use server'

import {Item} from "@/components/item-list";
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import isEmail from "validator/lib/isEmail"
import {redirect} from "next/navigation";

interface SearchData{
    uuid:string,
    nickName:string,
    avatar:string,
}

export const searchFriend = async (searchText:string):Promise<Item[]> =>{
    const session = await getServerSession(authOption as any) as Session;
    let result: Item[] = [];
    if(isEmail(searchText)){
        const res = await fetch(process.env.BACK_END_URL+`/friends/by-email?email=${searchText}`,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
                Authorization:`Bearer ${session.access_token}`
            },
            cache:"no-cache" && "no-store"
        })
        if(res.ok){
            const resData:SearchData = await res.json()
            let data:Item[] = []
            data.push({conversationName:resData.nickName,isRead:false,conversationID:resData.uuid})

            if(data.length > 0){
                return data
            }
        }
        return []
    }else {
        const res = await fetch(process.env.BACK_END_URL+`/friends/by-nick-name?nick-name=${searchText}`,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
                Authorization:`Bearer ${session.access_token}`
            },
            cache:"no-cache" && "no-store"
        })
        if(res.ok){

            const resData:SearchData[] = await res.json()
            let data:Item[] = []
            for(let item of resData){
                data.push({conversationName:item.nickName,isRead:false,conversationID:item.uuid})
            }
            if(data.length > 0){
                return data
            }
        }
        return []
    }

}