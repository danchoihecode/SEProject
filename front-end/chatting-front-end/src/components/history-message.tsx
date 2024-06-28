'use client'
import {useMutation} from "@tanstack/react-query";
import {getMessageHistory} from "@/server/history";
import Box from "@mui/material/Box";
import {useEffect} from "react";

export interface MessageHistory {
    messageType:string,
    messageContent:string,
    senderUserId:string,
    conversationId:string,
    senderName:string
}

interface MessageHistoryProps {
    conversationId:string,
    token:string,
    senderUserId:string
}

export default function HistoryMessage(props: MessageHistoryProps) {

    const {data,isPending,isError} = useMutation({
        mutationFn: async () =>{
          return await getMessageHistory({conservationId: props.conversationId, token: props.token}) as MessageHistory[]
        },
        onSuccess:() =>{
            console.log((data))
        }
    })

    useEffect(() => {

    }, []);

    if(isPending){
        return <Box sx={{
            justifyContent:'center',
            alignItems:'center',
            width:'100%'
        }}>
            Loading...
        </Box>
    }

    if(isError){
        return <Box sx={{
            justifyContent:'center',
            alignItems:'center',
            width:'100%',
            color:'error.primary'
        }}>
            Failed to load Previous Message
        </Box>
    }

    return <>
        1235
    </>
}

