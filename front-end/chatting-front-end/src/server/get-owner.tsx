'use server'
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";


export const getOwnerId = async (conversationId: string): Promise<string> => {
    const session = await getServerSession(authOption as any) as Session;
    const requestBody = {
        conversationId: conversationId
    };

    try {
        const response = await fetch('http://localhost:8080/owner-id', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            throw new Error(`Failed to fetch owner. Status: ${response.status}`);
        } else {
            const data: string = await response.text(); 
            return data;
        }
    } catch (error) {
        throw error;
    }
};
