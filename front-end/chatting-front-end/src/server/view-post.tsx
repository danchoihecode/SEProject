'use server'
import { getServerSession } from "next-auth/next";
import { authOption } from "@/configs/next-auth-config";
import { Session } from "next-auth";
import { PostDTO } from "@/dto/PostDTO";

export const getPosts = async (): Promise<PostDTO[]> => {
    const session = await getServerSession(authOption as any) as Session;

    try {
        const response = await fetch('http://localhost:8080/report_list', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Failed to fetch reports. Status: ${response.status}`);
        }

        const text = await response.text();
        const data: PostDTO[] = text ? JSON.parse(text) : [];

        return data;
    } catch (error) {
        throw error;
    }
};
