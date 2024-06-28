'use server'
import { getServerSession } from "next-auth/next";
import { authOption } from "@/configs/next-auth-config";
import { Session } from "next-auth";
import { MemberDTO } from "@/dto/MemberDTO";
export const searchUser = async (fullName: string): Promise<MemberDTO | null> => {
    const session = await getServerSession(authOption as any) as Session;
    const requestBody = {
        fullName: fullName
    };

    try {
        const response = await fetch('http://localhost:8080/search-user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            throw new Error(`Failed to search user. Status: ${response.status}`);
        } else {
            const data: MemberDTO = await response.json(); 
            return data; 
        }
    } catch (error) {
        throw error; 
    }
};

export const addMember = async (memberId: string, conversationId: string) => {
    const session = await getServerSession(authOption as any) as Session;
    const requestBody = {
        memberId: memberId,
        conversationId: conversationId
    };

    try {
        const response = await fetch('http://localhost:8080/add-member', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            throw new Error(`Failed to add member. Status: ${response.status}`);
        }

    } catch (error) {
        throw error;
    }
};

