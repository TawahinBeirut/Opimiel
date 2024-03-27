import { NextRequest, NextResponse } from "next/server";
import Prisma from "@/utils/PrismaClient";

export async function GET() {
  let res = await Prisma.subject.findMany({});

  return NextResponse.json({
    message: "Données recuperées",
    data: res,
  },{status:200,statusText:"oe"});
}
