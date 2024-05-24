'use client'
import {Button} from "@mui/material";
import {getSession, signOut} from "next-auth/react";
import {useRouter} from "next/navigation";

export default function TestPage(){
    const router = useRouter();
    return <div>
        This test page!!
        <Button onClick={async () => {
            await signOut({redirect:false})
        }}>Sign Out</Button>
    </div>
}