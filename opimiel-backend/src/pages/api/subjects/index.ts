import Prisma from "@/utils/PrismaClient";
import { NextApiRequest, NextApiResponse } from "next";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  if (req.method === "GET") {
    const response = await Prisma.subject.findMany();

    res.status(200).json({
      message: "Données récupérées",
      data: response,
    });
  }else{
    res.status(400).json({error: "THIS ROUTE ONLY ACCEPTS GET !"})
  }
}
