import Prisma from "@/utils/PrismaClient";
import { NextRequest, NextResponse } from "next/server";

export async function POST(request: NextRequest) {
  const { authorId, name } = await request.json();

  if (typeof authorId == "string" && typeof name == "string") {
    const user = await Prisma.user.findUnique({
      where: {
        id: authorId,
      },
    });
    if (!user) {
      return NextResponse.json({
        message: "Utilisateur non existant",
        statusbar: 401,
      });
    }
    const res = await Prisma.subject.create({
      data: {
        authorId: authorId,
        name: name,
      },
    });
    if (!res) {
      return NextResponse.json({
        message: "Operation non effectuée",
        statusbar: 402,
      });
    }
    return NextResponse.json({ message: "OE", statusbar: 200 });
  } else return NextResponse.json({ message: "OE", statusbar: 400 });
}
