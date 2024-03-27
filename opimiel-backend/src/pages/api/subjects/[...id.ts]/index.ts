import Prisma from "@/utils/PrismaClient";
import { NextApiRequest, NextApiResponse } from "next";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  if (req.method === "GET") {
    const { id } = await req.query;

    if (!id) {
      res.status(400).json({ error: "L'id n'es pas spécifié !" });
    }
    const response = await Prisma.subject.findUnique({
      where: {
        id: id as string,
      },
    });
    res.status(200).json({ data: response, message: "données récupérées" });
  }else{
    res.status(400).json({error: "ONLY GET REQUEST ARE ACCEPTED !"})
  }
}
