import Prisma from "@/utils/PrismaClient";
import { NextRequest, NextResponse } from "next/server";


export async function GET(request:NextRequest,params:{params : {id:string}}) {

    const id= params.params.id[0];    
    
    let res = await Prisma.favorite.findMany({
        where:{
            userId:id
        }
    })

    let dataResult = [];

    for(let i = 0;i<res.length;i++)
    {
        let tempRes = await Prisma.subject.findUnique({
            where:{
                id: res[i].subjectId
            }
        })
        if (tempRes) dataResult.push(tempRes);
    }

    return NextResponse.json({message:"oe",data:dataResult},{status:200})

}   