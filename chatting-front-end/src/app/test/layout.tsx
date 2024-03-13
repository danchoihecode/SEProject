import { Metadata } from "next";

export const metadata:Metadata = {
    title:"Test Page"
}

export default function TestLayout(
    {
        children,
      }: Readonly<{
        children: React.ReactNode;
      }>
){
    return children
}