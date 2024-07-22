'use client'

import Link from "next/link"
import { Breadcrumb, BreadcrumbItem, BreadcrumbLink, BreadcrumbList, BreadcrumbSeparator } from "@/components/ui/breadcrumb"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { ScrollArea } from '@/components/ui/scroll-area';
import { useState, useEffect } from "react";
import { UserDTO } from "@/dto/PostDTO";
import { getBannedUsers } from "@/server/view-banned-user";

export default function Posts() {

    const [posts, setPosts] = useState<UserDTO[]>([]);

    useEffect(() => {
        getBannedUsers().then((data) => {
            if (data) setPosts(data)
        })
    }, []);


    return (
        <ScrollArea className="h-full">
            <div className="flex min-h-screen w-full flex-col bg-muted/40">
                <div className="flex flex-col sm:gap-4 sm:py-4 sm:pl-14">
                    <header className="sticky top-0 z-30 flex h-14 items-center gap-4 bpost-b bg-background px-4 sm:static sm:h-auto sm:bpost-0 sm:bg-transparent sm:px-6">
                        <Breadcrumb className="hidden md:flex">
                            <BreadcrumbList>
                                <BreadcrumbItem>
                                    <BreadcrumbLink asChild>
                                        <Link href="/admin">Admin</Link>
                                    </BreadcrumbLink>
                                </BreadcrumbItem>
                                <BreadcrumbSeparator />
                                <BreadcrumbItem>
                                    <BreadcrumbLink asChild>
                                        <Link href="/admin/posts">Banned users</Link>
                                    </BreadcrumbLink>
                                </BreadcrumbItem>
                            </BreadcrumbList>
                        </Breadcrumb>
                    </header>
                    <PostTable posts={posts} />
                </div>
            </div>
        </ScrollArea>
    )
}

function PostTable({ posts }: { posts: UserDTO[] }) {

    return (
        <Card>
            <CardHeader>
                <CardTitle>Users</CardTitle>
                <CardDescription>Manage banned user.</CardDescription>
            </CardHeader>
            <CardContent>
                <Table>
                    <TableHeader>
                        <TableRow>
                            <TableHead>Id</TableHead>
                            <TableHead>User name</TableHead>
                            <TableHead>Ban reason</TableHead>
                            <TableHead>Duration</TableHead>
                            <TableHead>Banned by</TableHead>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        {posts.map((post, index) => (
                            <TableRow key={index}>
                                <TableCell>{index + 1}</TableCell>
                                <TableCell>{post.userName}</TableCell>
                                <TableCell>{post.reason}</TableCell>
                                <TableCell>{post.duration}</TableCell>
                                <TableCell>{post.admin}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </CardContent>
            <CardFooter>
                <div className="text-xs text-muted-foreground">
                    Showing <strong>1-{posts.length}</strong> of <strong>{posts.length}</strong> users
                </div>
            </CardFooter>
        </Card>
    )
}
