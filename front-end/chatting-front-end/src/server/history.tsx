import {MessageHistory} from "@/components/history-message";

interface HistoryActionProps {
    conservationId:string,
    token:string
}

export async function getMessageHistory(props:HistoryActionProps){
    const res = await fetch(process.env.BACK_END_URL +`/conservations/${props.conservationId}`,{
        method:'GET',
        headers:{
            "Content-Type":"application/json",
            Authorization:`Bearer ${props.token}`
        },
        cache:"no-store"
    })

    if(res.ok){
        try{
            const data:MessageHistory[] = await res.json()
            if(data)
            {
                console.log(data)
                return data
            }
            return  []
        }catch (e){
            return []
        }
    }
    return []
}