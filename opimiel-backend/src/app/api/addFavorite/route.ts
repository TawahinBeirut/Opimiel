import Prisma from "@/utils/PrismaClient";
import { NextRequest,NextResponse } from "next/server";

export async function POST(request:NextRequest) {
    let userId;
    let subjectId
    try {
        const body = await request.json();
        userId = body.userId;
        subjectId = body.subjectId
      } catch (e) {
        return NextResponse.json({ message: "pas Json" }, { status: 403 });
    }
    let res = await Prisma.favorite.findUnique({
        where:{
            userId_subjectId:{
                userId: userId,
                subjectId: subjectId
            }
        }
    })
    if (res) return NextResponse.json({message:"Deja en favoris"},{status:401})
    
    let res2 = await Prisma.favorite.create({
        data:{
            subjectId: subjectId,
            userId:userId
        }
    })
    
    if (res2) return NextResponse.json({message:"c tout bon"},{status:200})
    else return NextResponse.json({message:"ca a pas marché jsp pourquoi"},{status:402})
}