'use server'
import {Item} from "@/components/item-list";
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import {signOut} from "next-auth/react";
import {redirect} from "next/navigation";

interface ConservationData{
    conversationName:string,
    avatar:string[],
    isRead:boolean,
    conversationId:string,
    isGroup:boolean
}

export const getConversationList = async ():Promise<Item[]> =>{
    const session = await getServerSession(authOption as any) as Session
    try{
        const res = await fetch(process.env.BACK_END_URL +`/conversations/list?user-id=${session.id}`,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
                Authorization:`Bearer ${session.access_token}`
            },
            cache:"no-store"
        }).then((response:Response) => {
            if(response.ok){
                return response.json()
            }else{
                return null
            }
        }).then(
            data =>{
                if(data){
                    const resData:ConservationData[]= data
                    let dataR:Item[] = [];
                    for(let item of resData){
                        dataR.push({conversationName:item.conversationName,
                            isRead:item.isRead,
                            conversationID:item.conversationId,
                            isGroup:item.isGroup,
                            isFriend:true
                        })
                    }
                    return dataR
                }
            }
        )
        if(res){
            return res
        }
    }catch (e){
        return []
    }


    return []
}

interface User{
    nickName:string,
}

export const getUserName = async ()=>{
    'use server'

    const session = await getServerSession(authOption as any) as Session

    const res = await fetch(process.env.BACK_END_URL +`/users/${session.id}`,{
        method:'GET',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${session.access_token}`
        },
        cache:"no-store"
    })
    if(res.ok){
        try {
            const data:User = await res.json();
            if(data) {
                return data.nickName
            }
        }catch (e){
            signOut()
            redirect("/auth/login")
        }

    }
    return ''
}
