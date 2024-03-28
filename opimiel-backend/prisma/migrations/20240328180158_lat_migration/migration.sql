/*
  Warnings:

  - Added the required column `latitude` to the `Response` table without a default value. This is not possible if the table is not empty.
  - Added the required column `longitude` to the `Response` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "Response" ADD COLUMN     "latitude" TEXT NOT NULL,
ADD COLUMN     "longitude" DOUBLE PRECISION NOT NULL;
