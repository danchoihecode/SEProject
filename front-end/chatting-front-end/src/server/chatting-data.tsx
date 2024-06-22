
import {Item} from "@/components/item-list";
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";

interface ConservationData{
    conversationName:string,
    avatar:string[],
    isRead:boolean,
    conversationId:string,
    isGroup:boolean
}

export const getConversationList = async ():Promise<Item[]> =>{

    const session = await getServerSession(authOption as any) as Session

    const res = await fetch(process.env.BACK_END_URL +`/conversations/list?user-id=${session.id}`,{
        method:'GET',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        cache:"no-cache" && "no-store"
    })
    if(res.ok){
        try{
            const resData:ConservationData[] = await res.json()
            let data:Item[] = [];
            for(let item of resData){
                data.push({conversationName:item.conversationName,
                    isRead:item.isRead,
                    conversationID:item.conversationId,
                    isGroup:item.isGroup
                })
            }
            return data
        } catch (e){
            return []
        }


    }
    return []
}

export const getChatHistory = async ()=>{
    const session = await getServerSession(authOption as any) as Session
}

interface User{
    nickName:string,
}

export const getUserName = async ()=>{
    const session = await getServerSession(authOption as any) as Session

    const res = await fetch(process.env.BACK_END_URL +`/users/${session.id}`,{
        method:'GET',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        cache:"no-cache" && "no-store"
    })
    if(res.ok){
        const data:User = await res.json();
        console.log(data)
        if(data) {
            return data.nickName
        }
    }
    return ''
}
