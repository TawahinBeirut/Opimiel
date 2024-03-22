import { NextRequest, NextResponse} from "next/server";
import Prisma from "@/utils/PrismaClient";

export async function GET(req:NextRequest,params:{params : {id:string}}) {

    const id= params.params.id[0];

    let res = await Prisma.subject.findUnique({
        where:{
            id: id
        }
    })
    return NextResponse.json({data: res,message:"données récuperées"},{status:200})
}   