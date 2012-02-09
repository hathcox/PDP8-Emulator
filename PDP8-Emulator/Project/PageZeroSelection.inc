TITLE PageZeroSelection

.code

PageZeroSelection PROC uses eax
	mov eax , CurrentPageBit
	cmp eax , 0d
	je ZERO
	jmp ONE
ZERO:
	call PageZeroZero
	ret
ONE:
	call PageZeroOne
	ret
PageZeroSelection ENDP

PageZeroZero PROC uses eax esi edx ebx
	mov esi , OFFSET CurrentPage
	mov eax , CurrentPageCounter
	mov edx , 127d
	cmp edx , eax
	jc OVERFLOW
	jmp CONTINUE
OVERFLOW:
	mov eax , 0d
	mov CurrentPageCounter , eax
	jmp CONTINUE
CONTINUE:
	mov ebx , CurrentPageCounter
	mov edx , ebx
	imul edx , 4
	mov eax , CurrentAddress
	mov [esi + edx] , eax
	inc ebx
	mov CurrentPageCounter , ebx
	ret
PageZeroZero ENDP

PageZeroOne PROC
	mov esi , OFFSET PageZero
	mov eax , PageZeroCounter
	mov edx , 127d
	cmp edx , eax
	jc OVERFLOW
	jmp CONTINUE
OVERFLOW:
	mov eax , 0d
	mov CurrentPageCounter , eax
	jmp CONTINUE
CONTINUE:
	mov ebx , CurrentPageCounter
	mov edx , ebx
	imul edx , 4
	mov eax , CurrentAddress
	mov [esi + edx] , eax
	inc ebx
	mov CurrentPageCounter , ebx
	ret	
PageZeroOne ENDP

PageZeroPrint PROC uses eax esi

	mov esi , CurrentPageCounter
	sub esi , 1
	imul esi , 4
	mov eax , [esi + CurrentPage]
	call WriteBin
	ret

PageZeroPrint ENDP



