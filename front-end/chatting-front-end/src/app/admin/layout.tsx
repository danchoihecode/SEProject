import Header from '@/components/layout/header';
import AdminSidebar from '@/components/layout/admin-sidebar';
import type { Metadata } from 'next';
import { getServerSession } from "next-auth/next";
import { Session } from "next-auth";
import { authOption } from "@/configs/next-auth-config";
import { redirect } from "next/navigation";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export const metadata: Metadata = {
  title: 'Admin Panel',
  description: 'Admin panel'
};

export default async function AdminLayout({
  children
}: {
  children: React.ReactNode;
}) {
  const session = await getServerSession(authOption) as Session;
  if (!session || !session.access_token || !session.admin) {
      redirect("/auth/login");
  }
  return (
    <div>
      <Header />
      <div className="flex h-screen overflow-hidden">
        <AdminSidebar />
        <main className="flex-1 overflow-auto pt-16">{children}
        <ToastContainer />
        </main>
      </div>
    </div>
  );
}
