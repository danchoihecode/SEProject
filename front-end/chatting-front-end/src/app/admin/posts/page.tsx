'use client'

import Link from "next/link"
import { Breadcrumb, BreadcrumbItem, BreadcrumbLink, BreadcrumbList, BreadcrumbSeparator } from "@/components/ui/breadcrumb"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { ScrollArea } from '@/components/ui/scroll-area';
import { useState, useEffect } from "react";
import { PostDTO } from "@/dto/PostDTO";
import { getPosts } from "@/server/view-post";
import { removePost } from "@/server/remove-post";
import { approveReport } from "@/server/approve-report";
import { toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export default function Posts() {

  const [posts, setPosts] = useState<PostDTO[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [currentPostId, setCurrentPostId] = useState<string | null>(null);
  const [banReason, setBanReason] = useState('');
  const [duration, setDuration] = useState('');

  useEffect(() => {
    getPosts().then((data) => {
      if (data) setPosts(data)
    })
  }, []);

  const handleRemove = async (postId: string) => {
    try {
      await removePost(postId);
      setPosts(prevPosts => prevPosts.filter(post => post.reportID !== postId));
      toast.success("Report declined successfully !");
    } catch (error) {
      toast.error("Failed to decline Report !");
    }
  };

  const handleApprove = async (postId: string) => {
    setCurrentPostId(postId);
    setShowModal(true);
  };

  const handleSubmitApproval = async () => {
    if (currentPostId) {
      try {
        await approveReport(currentPostId, banReason, duration);
        setPosts(prevPosts => prevPosts.filter(post => post.reportID !== currentPostId));
        toast.success("The user has been banned !");
        setShowModal(false);
      } catch (error) {
        toast.error("Failed to approve report !");
      }
    }
  };

  const closeModal = () => {
    setShowModal(false);
    setCurrentPostId(null);
    setBanReason('');
    setDuration('');
  };

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
                    <Link href="/admin/posts">Reported posts</Link>
                  </BreadcrumbLink>
                </BreadcrumbItem>
              </BreadcrumbList>
            </Breadcrumb>
          </header>
          <PostTable posts={posts} onRemove={handleRemove} onApprove={handleApprove} />
        </div>
      </div>
      {showModal && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded shadow-lg">
            <h2 className="text-xl mb-4">Approve Post</h2>
            <div className="mb-4">
              <label className="block text-sm font-medium mb-2">Ban Reason</label>
              <input
                type="text"
                value={banReason}
                onChange={(e) => setBanReason(e.target.value)}
                className="w-full border border-gray-300 p-2 rounded"
              />
            </div>
            <div className="mb-4">
              <label className="block text-sm font-medium mb-2">Duration</label>
              <input
                type="text"
                value={duration}
                onChange={(e) => setDuration(e.target.value)}
                className="w-full border border-gray-300 p-2 rounded"
              />
            </div>
            <div className="flex justify-end">
              <button
                onClick={closeModal}
                className="px-4 py-2 bg-gray-200 text-gray-700 rounded mr-2"
              >
                Cancel
              </button>
              <button
                onClick={handleSubmitApproval}
                className="px-4 py-2 bg-blue-600 text-white rounded"
              >
                Approve
              </button>
            </div>
          </div>
        </div>
      )}
    </ScrollArea>
  )
}

function PostTable({ posts, onRemove, onApprove }: { posts: PostDTO[], onRemove: (postId: string) => void, onApprove: (postId: string) => void }) {

  return (
    <Card>
      <CardHeader>
        <CardTitle>Posts</CardTitle>
        <CardDescription>Manage reported posts.</CardDescription>
      </CardHeader>
      <CardContent>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Report ID</TableHead>
              <TableHead>Reported user name</TableHead>
              <TableHead>Post content</TableHead>
              <TableHead>Reason</TableHead>
              <TableHead>Actions</TableHead>
              <TableHead></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {posts.map((post, index) => (
              <TableRow key={index}>
                <TableCell>{post.reportID}</TableCell>
                <TableCell>{post.userName}</TableCell>
                <TableCell>{post.postText}</TableCell>
                <TableCell>{post.reason}</TableCell>
                <TableCell>
                  <button
                    onClick={() => onApprove(post.reportID)}
                    className="inline-block px-3 py-1 bg-black text-white text-sm rounded transition duration-300 hover:shadow-lg"
                  >
                    Approve
                  </button>
                </TableCell>
                <TableCell>
                  <button
                    onClick={() => onRemove(post.reportID)}
                    className="inline-block px-3 py-1 bg-black text-white text-sm rounded transition duration-300 hover:shadow-lg"
                  >
                    Decline
                  </button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
      <CardFooter>
        <div className="text-xs text-muted-foreground">
          Showing <strong>1-{posts.length}</strong> of <strong>{posts.length}</strong> Posts
        </div>
      </CardFooter>
    </Card>
  )
}
