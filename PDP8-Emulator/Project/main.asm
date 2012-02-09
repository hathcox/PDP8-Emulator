TITLE PDP8 Simulator Part1					(main.asm)

; Description: Instruction Decoder

INCLUDE Irvine32.inc
INCLUDE OPROppCodeSelection.inc
INCLUDE OPROppCodeOne.inc
INCLUDE OPROppCodeTwo.inc
INCLUDE PageZeroSelection.inc
INCLUDE IOTOppCode.inc

.data

Accumulator DWORD 0
ProgramCounter DWORD 0
Link DWORD 0
Memory DWORD 110000100000b , 110000011000b , 110000100000b , 110000011000b , 110000100000b , 110000011000b , 110000100000b , 110000011000b , 110000100000b 
PageZero DWORD 128 DUP(0)
PageZeroCounter DWORD 0
CurrentPage DWORD 128 DUP(0)
CurrentPageCounter DWORD 0
ReadyFlag DWORD 0

xtable BYTE "0123456789ABCDEF"

LastInstruction DWORD 0
CurrentInstruction DWORD 0
CurrentOppCode DWORD 0
CurrentIndirectBit DWORD 0
CurrentPageBit DWORD 0
CurrentAddress DWORD 0
CurrentOPROppCode DWORD 0

DecoderMessageOne BYTE "The Current OppCode is: ", 0
DecoderMessageTwo BYTE "The Current IndirectBit is: " , 0
DecoderMessageThree BYTE "The Current PageBit is: " , 0
DecoderMessageFour BYTE "The Current Address is: " , 0

FindOppCodeMessageOne BYTE "Your OppCode was: " , 0

OppCodes BYTE "ADD", 0, "TAD", 0, "ISZ", 0, "DCA", 0, "JMS", 0, "JMP", 0, "IOT", 0, "OPR", 0

ExecuteOppCodeMessageOne BYTE "Instruction Not Yet Implemented " , 0

PDPFinishedRunning BYTE "Your Program has finished running!! " , 0

IOTOppCodeCounter DWORD 0
IOTOppCodeStr BYTE " " , 0 , "T" , 0 , "E" , 0 , "S" , 0 , "T" , 0 
IOTOppCodeMessageOne BYTE "Unknown Device" , 0

OppCodeArray DWORD ANDOppCode , TADOppCode , ISZOppCode , DCAOppCode , JMSOppCode , JMPOppCode , IOTOppCode , OPROppCode
OPROppCodeArrayOne DWORD IACOppCode , BSWOppCode , RALOppCode , RAROppCode , CMLOppCode , CMAOppCode , CLLOppCode , CLAOppCode
OPROppCodeArrayTwoOr DWORD NOPOppCode , HLTOppCode , OSROppCode , NOPOppCode , SNLOppCode , SZAOppCode , SMAOppCode , CLAOppCode
OPROppCodeArrayTwoAnd DWORD NOPOppCode , HLTOppCode , OSROppCode , NOPOppCode , SZLOppCode , SNAOppCode , SPAOppCode , CLAOppCode
OPROppCodeCounter DWORD 0
.code

main PROC
	call Simulator

	ret
main ENDP

Simulator PROC uses eax edx
	mov eax , 0d
CYCLE:
	cmp eax , 10d
	je FINISHED
	call GetInstruction
	push LastInstruction
	call LoadInstruction
	call ExecuteOppCode
	call ExecutePageZero
	pop LastInstruction
	inc eax
	jmp CYCLE
FINISHED:
	call crlf
	mov edx , OFFSET PDPFinishedRunning
	call WriteString
	ret
Simulator ENDP

ExecuteOppCode PROC uses esi eax ecx
; ===========================================================================================
; Call this after LoadInstruction and LoadOppCodePtr inorder to execute the current OppCode
;============================================================================================
	
	mov esi , CurrentOppCode
	imul esi , 4
	mov eax , [esi + OppCodeArray]
	call eax

	ret
ExecuteOppCode ENDP

ANDOppCode PROC uses eax

	mov eax , Accumulator
	and eax , CurrentAddress
	mov Accumulator , eax

	ret
AndOppCode ENDP

TADOppCode PROC uses eax

	mov eax , Accumulator
	add eax , CurrentAddress
	mov Accumulator , eax

	ret
TADOppCode ENDP

ISZOppCode PROC uses eax

	mov eax , CurrentAddress
	add eax , 1d
	cmp eax , 128d
	je SKIP
	ret

SKIP:
	push eax
	mov eax , ProgramCounter
	add eax , 1d
	mov ProgramCounter , eax
	pop eax
	ret
ISZOppCode ENDP

DCAOppCode PROC uses eax

	mov eax , Accumulator
	mov CurrentAddress , eax
	mov Accumulator , 0d

	ret
DCAOppCode ENDP

JMSOppCode PROC uses eax

	mov eax , CurrentAddress
	mov ProgramCounter , eax
	mov CurrentAddress , eax
	add eax , 1d
	mov ProgramCounter , eax

	ret
JMSOppCode ENDP

JMPOppCode PROC uses eax

	mov eax , CurrentAddress
	mov ProgramCounter , eax

	ret
JMPOppCode ENDP

IOTOppCode PROC uses eax edx
	call IOTDeviceSelection
	ret
IOTOppCode ENDP

OPROppCode PROC 
	call OPROppCodeSelection
	ret
OPROppCode ENDP

NOPOppCode PROC 
	ret
NOPOppCode ENDP

FindOppCode PROC uses eax edx
; ====================================================================================
; FindOppCode should be called after LoadInstruction to determine the current OppCode
; ====================================================================================
	mov eax , CurrentOppCode
	imul eax , 4
	mov edx , OFFSET OppCodes
	add edx , eax
	call WriteString

	ret
FindOppCode ENDP

DisplayInstruction PROC uses edx eax
; ==============================================================
; Display Instruction should be called after LoadInstruction 
; and will display the current instruction broken into peices
; ==============================================================

	mov edx , OFFSET DecoderMessageOne
	call WriteString
  	mov eax , CurrentOppCode
	call WriteDec
	call crlf

	mov edx , OFFSET DecoderMessageTwo
	call WriteString
	mov eax , CurrentIndirectBit
	call WriteDec
	call crlf

	mov edx , OFFSET DecoderMessageThree
	call WriteString
	mov eax , CurrentPageBit
	call WriteDec
	call crlf

	mov edx , OFFSET DecoderMessageFour
	call WriteString
	mov eax , CurrentAddress
	call WriteBin
	call crlf

	ret
DisplayInstruction ENDP

GetInstruction PROC uses esi eax edx
; ==========================================
; Grabs the next instruction from "Memory" 
; ==========================================
	mov esi , ProgramCounter
	mov edx , esi
	imul edx , 4
	mov eax , [edx + Memory]
	mov LastInstruction , eax
	inc esi
	mov ProgramCounter , esi

	ret
GetInstruction ENDP

ExecutePageZero PROC uses eax
	call PageZeroSelection
	ret
ExecutePageZero ENDP

ExecuteIndirect PROC uses eax
	ret
ExecuteIndirect ENDP

LoadInstruction PROC uses eax,
Instruction:DWORD

; =============================================================================
; LoadInstruction should be called after you push a 12 bit binary instruction
; and will load that address into the previously declared variables
; =============================================================================
	
	mov eax , Instruction
	mov CurrentInstruction , eax

	mov eax , Instruction
	and eax , 111000000000b
	shr eax , 9
	mov CurrentOppCode , eax

	mov eax , Instruction
	and eax , 000100000000b
	shr eax , 8
	mov CurrentIndirectBit , eax

	mov eax , Instruction
	and eax , 000010000000b
	shr eax , 7
	mov CurrentPageBit , eax

	mov eax , Instruction
	and eax , 000001111111b
	mov CurrentAddress , eax

	ret
	
LoadInstruction ENDP

DigitToAscii PROC uses ebx
; =============================================
; translates a digit into its ascii equivilent
; =============================================
	 mov   ebx , OFFSET xtable
	 xlat
	 ret
DigitToAscii ENDP

END main