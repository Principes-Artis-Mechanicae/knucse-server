#!/bin/bash

# 폴더 경로와 출력 파일 이름을 인자로 받음
folder_path="$1"
output_file="$2"

# 사용법 안내
if [ -z "$folder_path" ] || [ -z "$output_file" ]; then
  echo "사용법: $0 <폴더_경로> <출력_파일명>"
  exit 1
fi

# 기존에 존재하는 출력 파일 삭제 (있을 경우)
if [ -f "$output_file" ]; then
  rm "$output_file"
  echo "기존의 $output_file 파일을 삭제했습니다."
fi

# 특정 확장자를 지정 (예: .txt, .sh 등 원하는 확장자로 변경)
file_extension=".java"

# 특정 확장자를 가진 파일을 재귀적으로 찾아 처리
find "$folder_path" -type f -name "*$file_extension" | while read -r file; do
  echo "Processing $file"

  # 파일 경로 출력
  echo "파일 경로: $file" >> "$output_file"

  # 파일 이름 출력
  echo "파일 이름: $(basename "$file")" >> "$output_file"

  # 파일 내용 출력
  echo "파일 내용:" >> "$output_file"
  cat "$file" >> "$output_file"

  # 파일 간에 구분을 위한 구분선 추가
  echo -e "\n------------------------------\n" >> "$output_file"
done

echo "모든 *$file_extension 파일이 $output_file 에 저장되었습니다."
