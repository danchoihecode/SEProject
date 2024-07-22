'use client'
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "./card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "./table"
import { ScrollArea } from './scroll-area';
import { useState, useEffect, useContext } from "react"
import { MemberDTO } from "@/dto/MemberDTO";
import { getMembers } from "@/server/view-member";
import { removeMember } from "@/server/remove-member";
import { SelectedRoomContext } from "@/context/selected-room-context";
import { getOwnerId } from '@/server/get-owner';
import { toast, ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import { useRouter } from 'next/navigation';

const Members: React.FC<{ userId: string }> = ({ userId }) => {

  const router = useRouter();
  const { selectedIndex, setSelectedIndex } = useContext(SelectedRoomContext);
  const [members, setMembers] = useState<MemberDTO[]>([]);
  const [ownerId, setOwnerId] = useState('');

  useEffect(() => {
    const selectedConversationID = localStorage.getItem('selectedConversationID');
    if (selectedConversationID) {
      setSelectedIndex(selectedConversationID);
    }
  }, [setSelectedIndex]);

  useEffect(() => {
    if (selectedIndex && selectedIndex !== '0') {
      getMembers(selectedIndex).then((data) => {
        if (data) setMembers(data);
      });
      getOwnerId(selectedIndex).then((data) => {
        if (data) setOwnerId(data);
      });
    }
  }, [selectedIndex]);

  const handleRemove = async (memberId: string) => {
    // alert(userId + ' and ' + ownerId);
    if (userId !== ownerId) {
      toast.error('You do not have permission to perform this action.');
      return;
    }
    if (memberId === userId) {
      toast.error('You can not remove youself !');
      return;
    }

    try {
      await removeMember(memberId, selectedIndex);
      setMembers(prevMembers => prevMembers.filter(member => member.id !== memberId));
      toast.success("Member removed successfully !");
    } catch (error) {
      toast.error("Failed to remove member !");
    }
  };

  const onViewProfile = (id: string) => {
    router.push(`/home/post/${id}`);
  };

  return (
    <ScrollArea className="h-full">
      <div className="flex min-h-screen w-full flex-col bg-muted/40">
        <div className="flex flex-col sm:gap-4 sm:py-4 sm:pl-14">
          <MemberTable members={members} onRemove={handleRemove} onViewProfile={onViewProfile} />
        </div>
      </div>
      <ToastContainer />
    </ScrollArea>
  );
}

function MemberTable({ members, onRemove, onViewProfile }: { members: MemberDTO[], onRemove: (memberId: string) => void, onViewProfile: (id: string) => void }) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Members in this group</CardTitle>
      </CardHeader>
      <CardContent>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>STT</TableHead>
              <TableHead>Full name</TableHead>
              <TableHead>Nick name</TableHead>
              <TableHead>Email</TableHead>
              <TableHead>Action</TableHead>
              <TableHead>Profile</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {members.map((member, index) => (
              <TableRow key={index}>
                <TableCell>{index + 1}</TableCell>
                <TableCell>{member.fullName}</TableCell>
                <TableCell>{member.nickName}</TableCell>
                <TableCell>{member.email}</TableCell>
                <TableCell>
                  <button
                    onClick={() => onRemove(member.id)}
                    className="inline-block px-3 py-1 bg-black text-white text-sm rounded transition duration-300 hover:shadow-lg"
                  >
                    Remove
                  </button>
                </TableCell>
                <TableCell>
                  <button
                    onClick={() => onViewProfile(member.id)}
                    className="inline-block px-3 py-1 bg-black text-white text-sm rounded transition duration-300 hover:shadow-lg"
                  >
                    Profile
                  </button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
      <CardFooter>
        <div className="text-xs text-muted-foreground">
          Showing <strong>1-{members.length}</strong> of <strong>{members.length}</strong> Members
        </div>
      </CardFooter>
    </Card>
  );
}

export default Members;
