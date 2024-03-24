import Prisma from "@/utils/PrismaClient";
import { NextRequest, NextResponse } from "next/server";

export async function POST(request: NextRequest) {
    let value;
    let authorId;
    let subjectId
    try {
        const body = await request.json();
        value = body.value;
        authorId = body.authorId;
        subjectId = body.subjectId
      } catch (e) {
        return NextResponse.json({ message: "pas Json" }, { status: 403 });
    }
    let res = await Prisma.response.findUnique({
        where:{ 
            authorId_subjectId:{
                authorId: authorId,
                subjectId: subjectId
            }
        }
    })
    if (res) return NextResponse.json({message:"Ce mec a deja repondu "},{status:401})

    let res2 = await Prisma.response.create({
        data:{
            value: value,
            authorId: authorId,
            subjectId: subjectId
        }
    });
    if (res2) return NextResponse.json({message:"c tout bon"},{status:200})
    else return NextResponse.json({message:"ca a pas marché jsp pourquoi"},{status:402})
}