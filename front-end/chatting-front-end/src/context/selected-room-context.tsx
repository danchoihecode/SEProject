'use client'
import {createContext, ReactNode, useState} from "react";

export type SelectedRoomContextType = {
    selectedIndex:string,
    setSelectedIndex:(value:string) => void
}

export const SelectedRoomContext =  createContext<SelectedRoomContextType>(
    {selectedIndex:'0',setSelectedIndex:()=>{}})
export const SelectedRoomContextProvider = ({children}:{children:ReactNode}) =>{
    const [selectedIndex, setSelectedIndex] = useState('0')

    return <SelectedRoomContext.Provider value={{selectedIndex,setSelectedIndex}}>
        {children}
    </SelectedRoomContext.Provider>
}