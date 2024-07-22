'use server'
import { getServerSession } from "next-auth/next";
import { authOption } from "@/configs/next-auth-config";
import { Session } from "next-auth";
import { Post } from "@/components/post-area";

export const getPostList = async (userId: string): Promise<Post[]> => {
    const session = await getServerSession(authOption as any) as Session;

    try {
        const response = await fetch(`http://localhost:8080/userprofile?id=${userId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Failed to fetch posts. Status: ${response.status}`);
        }

        const text = await response.text();
        const userProfile = text ? JSON.parse(text) : null;

        if (!userProfile || !userProfile.postList) {
            return [];
        }

        const data: Post[] = userProfile.postList.map((post: any) => ({
            id: post.id.toString(),
            content: post.postText,
            date: post.postDate,
            likes: post.noLike
        }));

        return data;

    } catch (error) {
        throw error;
    }
};
