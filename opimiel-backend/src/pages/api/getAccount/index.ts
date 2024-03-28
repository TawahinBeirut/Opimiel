import Prisma from "@/utils/PrismaClient";
import { NextApiRequest, NextApiResponse } from "next"

export default async function handler(req: NextApiRequest, res: NextApiResponse){
    if(req.method === "POST"){
        const {email} = req.body;

        if(!email){
            res.status(400).json({error: "Email not specified !"})
            return;
        }
        const account = await Prisma.user.findUnique({
            where:{
                email: email
            }
        })
        if(!account){
            res.status(404).json({error: "User not found !"})
            return;
        }
        res.status(200).json({userid: account.id})
    }else{
        res.status(405).json({error: "THIS ROUTE ONLY ACCEPT POST REQUEST !"})
    }

}