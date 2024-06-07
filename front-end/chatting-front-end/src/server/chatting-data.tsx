
import {Item} from "@/components/item-list";

export const getConversationList = () =>{
    const chats:Item[] = [
        {conversationID:1,name:'test1',new:false},
        {conversationID:2,name:'test2',currentMessage:'ABCDE',new:false},
        {conversationID:3,name:'test3',new:true},
        {conversationID:4,name:'test4',currentMessage:"BCassssasdsadsgfhdadasssssssDE",new:true}
    ]
    return chats;
}