import Prisma from "@/utils/PrismaClient";
import { NextRequest, NextResponse } from "next/server";

export async function POST(request: NextRequest) {
  let authorId;
  let name;
  try {
    const body = await request.json();
    authorId = body.authorId;
    name = body.name;
  } catch (e) {
    return NextResponse.json({ message: "pas Json" }, { status: 403 });
  }

  if (typeof authorId == "string" && typeof name == "string") {
    const user = await Prisma.user.findUnique({
      where: {
        id: authorId,
      },
    });
    if (!user) {
      return NextResponse.json(
        {
          message: "Utilisateur non existant",
        },
        { status: 403 }
      );
    }
    const res = await Prisma.subject.create({
      data: {
        authorId: authorId,
        name: name,
      },
    });
    if (!res) {
      return NextResponse.json(
        {
          message: "Operation non effectuée",
        },
        { status: 402 }
      );
    }
    return NextResponse.json({ message: "OE" }, { status: 200 });
  } else return NextResponse.json({ message: "NAn" }, { status: 378 });
}
