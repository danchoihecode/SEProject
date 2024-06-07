'use client'
import {SettingsContext, SettingsContextValue} from "@/context/settingsContext";
import {useContext} from "react";

export const useSettings = ():SettingsContextValue => useContext(SettingsContext);