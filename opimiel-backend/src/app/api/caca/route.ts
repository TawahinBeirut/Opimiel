
import Prisma from "@/utils/PrismaClient"
import { NextRequest, NextResponse } from "next/server";

export async function GET(res:NextResponse) {
    await Prisma.user.create({
        data:{
            
        }
    })
}