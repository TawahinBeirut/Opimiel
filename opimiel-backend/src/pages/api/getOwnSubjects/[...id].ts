import { NextApiRequest, NextApiResponse } from "next";
import Prisma from "@/utils/PrismaClient";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  if (req.method === "GET") {
    const { id } = await req.query;
    if (!id) {
      res.status(400).json({ error: "L'id n'es pas spécifié !" });
      return;
    }
    const response = await Prisma.subject.findMany({
      where: {
        authorId: id as string,
      },
    });
    if (!response) {
      res.status(400).json({ error: "L'id n'existe pas !" });
      return;
    }

    let dataResult = [];
    for (let i = 0; i < response.length; i++) {
      const element = response[i];

      let tempRes = await Prisma.subject.findUnique({
        where: {
          id: element.id
        },
      });
      if (tempRes) {
        dataResult.push(tempRes);
      }
    }

    res.status(200).json({ message: "oe", data: dataResult });
  }else{
    res.status(400).json({error: "ONLY GET ARE AUTHORIZED !"})
  }
}
