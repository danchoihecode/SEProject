'use server'
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";

export const approveReport = async (reportId:string, banReason: string, duration: string) => {
    const session = await getServerSession(authOption as any) as Session;
    const requestBody = {
        banReason: banReason,
        duration: parseInt(duration, 10)
    };

    try {
        const response = await fetch(`http://localhost:8080/admin/approveReport/${reportId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            throw new Error(`Failed to ban user. Status: ${response.status}`);
        }

    } catch (error) {
        throw error;
    }
};