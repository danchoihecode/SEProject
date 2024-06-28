'use server'
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import {MemberDTO } from "@/dto/MemberDTO";


export const getMembers = async (conversationId: string): Promise<MemberDTO[]> => {
    const session = await getServerSession(authOption as any) as Session;
    const requestBody = {
        conversationId: conversationId
    };

    try {
        const response = await fetch('http://localhost:8080/view-members', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            throw new Error(`Failed to fetch members. Status: ${response.status}`);
        } else {
            const data: MemberDTO[] = await response.json(); 
            return data;
        }
    } catch (error) {
        throw error;
    }
};
