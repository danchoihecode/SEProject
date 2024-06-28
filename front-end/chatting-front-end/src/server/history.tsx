'use server'
import {MessageHistory} from "@/components/chatting-box";
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";

interface HistoryActionProps {
    conservationId:string
}

export const getMessageHistory = async (props:HistoryActionProps):Promise<MessageHistory[]> =>{
    const session = await getServerSession(authOption as any) as Session
    const res = await fetch(`http://localhost:8080/conversations/messages?id=${props.conservationId}`,{
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