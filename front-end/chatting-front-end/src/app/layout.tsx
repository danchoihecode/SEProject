import type { Metadata } from "next";
import React from "react";

import {SettingsConsumer, SettingsProvider} from "@/context/settingsContext";
import {AppRouterCacheProvider} from "@mui/material-nextjs/v13-appRouter";

export const metadata: Metadata = {
  title: "Chatting Web",
  description: "Created By Group 15 ICT SE",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
      <html>
          <body>
              <AppRouterCacheProvider>
                  <SettingsProvider>
                      <SettingsConsumer>
                          {children}
                      </SettingsConsumer>
                  </SettingsProvider>
              </AppRouterCacheProvider>
          </body>
      </html>

  );
}
