import { Skeleton } from "@/components/ui/skeleton";
import { Card, CardContent, CardHeader } from "@/components/ui/card";

export default function UserFormSkeleton () {
  return (
    <div className="flex min-h-screen w-full flex-col bg-muted/40">
      <div className="flex flex-col sm:gap-4 sm:py-4 sm:pl-14">
        <header className="sticky top-0 z-30 flex h-14 items-center gap-4 border-b bg-background px-4 sm:static sm:h-auto sm:border-0 sm:bg-transparent sm:px-6">
          <Skeleton className="h-8 w-1/4" />
        </header>
        <main className="flex-1 gap-4 p-4 sm:px-6 sm:py-0 md:gap-8">
          <div className="mx-auto max-w-[59rem] flex-1 auto-rows-max gap-4">
            <div className="auto-rows-max items-start gap-4 lg:col-span-2 lg:gap-8">
              <Card x-chunk="dashboard-08-chunk-0">
                <CardHeader>
                  <Skeleton className="h-6 w-1/4" />
                  <Skeleton className="h-4 w-1/2" />
                </CardHeader>
                <CardContent>
                  <div className="grid gap-6">
                    <div className="grid gap-3">
                      <Skeleton className="h-4 w-1/5" />
                      <Skeleton className="h-10 w-full" />
                    </div>
                    <div className="grid gap-3">
                      <Skeleton className="h-4 w-1/5" />
                      <Skeleton className="h-10 w-full" />
                    </div>
                    <div className="grid gap-3">
                      <Skeleton className="h-4 w-1/5" />
                      <Skeleton className="h-10 w-full" />
                    </div>
                    <div className="grid gap-3">
                      <Skeleton className="h-4 w-1/5" />
                      <Skeleton className="h-10 w-full" />
                    </div>
                    <div className="grid gap-3">
                      <Skeleton className="h-4 w-1/5" />
                      <Skeleton className="h-10 w-full" />
                    </div>
                    <div className="grid gap-3">
                      <Skeleton className="h-4 w-1/5" />
                      <Skeleton className="h-10 w-full" />
                    </div>
                    <div className="hidden items-center gap-2 md:ml-auto md:flex">
                      <Skeleton className="h-8 w-16" />
                      <Skeleton className="h-8 w-24" />
                    </div>
                  </div>
                </CardContent>
              </Card>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
};
