
import Prisma from "@/utils/PrismaClient"
import { NextRequest, NextResponse } from "next/server";

export async function POST(req:NextRequest) {

    let email;
    let password;
    let name;
    try{
    const body = await req.json();
    email = body.email;
    password = body.password;
    name = body.name;
    }catch(e){
        return NextResponse.json({pe:"o"},{status:403})
    }   

    if (typeof email == "string" && typeof password == "string" && typeof name == "string"){
    let res = await Prisma.user.create({
        data:{
            email: email,
            name: name,
            password: password
        }
    })
    return NextResponse.json({oe:"oe",id:res.id},{status: 200})
}
    else return NextResponse.json({oe:"nan"},{status:378})
}